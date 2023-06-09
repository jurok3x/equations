package com.yukotsiuba.equation.dao.impl;

import com.yukotsiuba.equation.dao.IRootDao;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@PropertySource(value = "classpath:/db/root/root_queries.properties")
@Slf4j
public class RootDaoImpl implements IRootDao {

    private final NamedParameterJdbcTemplate template;
    private final RowMapper<Root> rowMapper;
    @Value("${save_root}")
    private String saveQuery;
    @Value("${find_root.by_id}")
    private String findByIdQuery;
    @Value("${find_root.by_value}")
    private String findByValueQuery;
    @Value("${find_root.by_equation}")
    private String findByEquationQuery;
    
    @Override
    public Root save(Root root) {
        MapSqlParameterSource params = new MapSqlParameterSource();

        KeyHolder keyHolder = new GeneratedKeyHolder();

        params.addValue("value", root.getValue());

        template.update(saveQuery, params, keyHolder, new String[] { "id" });
        Integer id = 0;
        if(keyHolder.getKey() != null) {
            id = keyHolder.getKey().intValue();
        }

        root.setId(id);

        return root;
    }

    @Override
    public Optional<Root> findById(Integer id) {
        SqlParameterSource param = new MapSqlParameterSource("id", id);
        Root root = null;
        try {
            root = template.queryForObject(findByIdQuery, param, rowMapper);
        } catch (DataAccessException ex) {
            log.error(String.format("Equation with id - %d, not found.", id));
        }
        return Optional.ofNullable(root);
    }
    
    @Override
    public Optional<Root> findByValue(String value) {
        SqlParameterSource param = new MapSqlParameterSource("value", value);
        Root root = null;
        try {
            root = template.queryForObject(findByValueQuery, param, rowMapper);
        } catch (DataAccessException ex) {
            log.error(String.format("Equation with value - %s, not found.", value));
        }
        return Optional.ofNullable(root);
    }

    @Override
    public List<Root> findByEquation(Equation equation) {
        SqlParameterSource parameterSource = new MapSqlParameterSource("equationId", equation.getId());
        return template.query(findByEquationQuery, parameterSource, rowMapper);
    }
}
