package com.yukotsiuba.equation.dao.impl;

import com.yukotsiuba.equation.dao.IEquationDao;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.relational.core.sql.In;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@PropertySource(value = "classpath:/db/equation/equation_queries.properties")
@Slf4j
public class EquationDaoImpl implements IEquationDao {

    private final NamedParameterJdbcTemplate template;
    private final RowMapper<Equation> rowMapper;
    @Value("${save}")
    private String saveQuery;
    @Value("${find.by_id}")
    private String findByIdQuery;
    @Value("${find.by_eq_string}")
    private String findByEqStringQuery;
    @Value("${find.by_roots}")
    private String findByRootsQuery;

    @Override
    public Equation save(Equation equation) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        KeyHolder keyHolder = new GeneratedKeyHolder();

        params.addValue("equation", equation.getEqString());

        template.update(saveQuery, params, keyHolder, new String[] { "id" });
        Integer id = 0;
        if(keyHolder.getKey() != null) {
            id = keyHolder.getKey().intValue();
        }

        equation.setId(id);

        return equation;
    }

    @Override
    public Optional<Equation> findById(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        Equation equation = null;
        try {
            equation = template.queryForObject(findByIdQuery, param, rowMapper);
        } catch (DataAccessException ex) {
            log.error(String.format("Equation with id - %d, not found.", id));
        }
        return Optional.ofNullable(equation);
    }

    @Override
    public Optional<Equation> findByEquationString(String eqString) {
        SqlParameterSource param = new MapSqlParameterSource("eqString", eqString);
        Equation equation = null;
        try {
            equation = template.queryForObject(findByIdQuery, param, rowMapper);
        } catch (DataAccessException ex) {
            log.error(String.format("Equation %s, not found.", eqString));
        }
        return Optional.ofNullable(equation);
    }

    @Override
    public List<Equation> findByRoots(List<Root> roots) {
        List<Integer> rootsId = roots.stream().mapToInt(Root::getId).collect(Collectors.toList());
        SqlParameterSource param = new MapSqlParameterSource("roots", rootsId);
        return template.query(findByRootsQuery, param, rowMapper);
    }

    @Override
    public List<Equation> findByRootCount(Integer count) {
        return null;
    }
}
