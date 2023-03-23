package com.yukotsiuba.equation.dao.impl;

import com.yukotsiuba.equation.dao.IRootDao;
import com.yukotsiuba.equation.entity.Equation;
import com.yukotsiuba.equation.entity.Root;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@PropertySource(value = "classpath:/db/root/root_queries.properties")
@Slf4j
public class RootDaoImpl implements IRootDao {
    @Override
    public Root save(Root root) {
        return null;
    }

    @Override
    public Root findByValue(Double value) {
        return null;
    }

    @Override
    public List<Root> findByEquation(Equation equation) {
        return null;
    }
}
