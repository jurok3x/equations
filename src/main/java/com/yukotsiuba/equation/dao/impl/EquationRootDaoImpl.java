package com.yukotsiuba.equation.dao.impl;

import com.yukotsiuba.equation.dao.IEquationRootDao;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@PropertySource(value = "classpath:/db/equations_roots/equations_roots_queries.properties")
@Slf4j
public class EquationRootDaoImpl implements IEquationRootDao {

    private final NamedParameterJdbcTemplate template;
    @Value("${save}")
    private String saveQuery;

    @Override
    public void save(Equation equation, Root root) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("equationId", equation.getId());
        params.addValue("rootId", root.getId());
        template.update(saveQuery, params);
        log.info("Inserting equation id {} and root id {}", equation.getId(), root.getId());
    }
}
