package Repository;

import Model.ExcelTemplateVO;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUploadDao extends BaseDAO{

    private JdbcTemplate template;

    public FileUploadDao(JdbcTemplate template){
        this.template=template;
    }

    public boolean saveFileDataInDB(List<ExcelTemplateVO> employeeList){
        String sql = "insert into EMPLOYEE (EMPLOYEEID, EMPLOYEENAME, ADDRESS, COUNTRY) "
                + " VALUES (:employeeId, :employeeName, :address, :country)";
        try {
            List<Map<String, String>> batchUpdateParams = new ArrayList<>();

            for(ExcelTemplateVO vo : employeeList){
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("employeeId", vo.getEmployeeId());
                paramMap.put("employeeName", vo.getEmployeeName());
                paramMap.put("address", vo.getAddress());
                paramMap.put("country", vo.getCountry());
                batchUpdateParams.add(paramMap);
            }

            getNamedParamJdbcTemplate().batchUpdate(sql, batchUpdateParams.toArray(new Map[batchUpdateParams.size()]));

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;

    }

    public List<ExcelTemplateVO> getAllData(){
        String sql = "SELECT * FROM EMPLOYEE";
        return template.query(sql, new RowMapper<ExcelTemplateVO>() {
            @Override
            public ExcelTemplateVO mapRow(ResultSet rs, int rowNum) throws SQLException {
                ExcelTemplateVO templateVO = new ExcelTemplateVO();
                templateVO.setEmployeeId(rs.getString("employeeId"));
                templateVO.setEmployeeName(rs.getString("employeeName"));
                templateVO.setAddress(rs.getString("address"));
                templateVO.setCountry(rs.getString("country"));
                return templateVO;
            }
        });
    }



}
