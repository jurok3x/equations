package com.yukotsiuba.equation.dao.extractor;

import com.yukotsiuba.equation.entity.Root;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class RootRowMapper implements RowMapper<Root> {
    @Override
    public Root mapRow(ResultSet rs, int rowNum) throws SQLException {
        return Root.builder()
                .id(rs.getInt("id"))
                .value(rs.getString("root_value"))
                .build();
    }
}
