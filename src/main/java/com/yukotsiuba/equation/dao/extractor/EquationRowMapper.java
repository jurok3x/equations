package com.yukotsiuba.equation.dao.extractor;

import com.yukotsiuba.equation.entity.Equation;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class EquationRowMapper implements RowMapper<Equation> {
    @Override
    public Equation mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Equation.builder()
                .id(rs.getInt("id"))
                .eqString("equation")
                .build();
    }
}
