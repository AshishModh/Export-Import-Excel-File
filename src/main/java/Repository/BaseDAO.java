package Repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

public abstract class BaseDAO {

    private DataSource dataSource = null;
    private NamedParameterJdbcTemplate namedParamJdbcTemplate = null;

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(final DataSource dataSource) {
        this.dataSource = dataSource;
        if(namedParamJdbcTemplate == null) {
            namedParamJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        }
    }

    public NamedParameterJdbcTemplate getNamedParamJdbcTemplate() {
        return namedParamJdbcTemplate;
    }
}
