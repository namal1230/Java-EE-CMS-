package org.example.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ComplaintDTO {
    private Integer id;
    private Integer employee_id;
    private String description;
    private String date;
    private String status;

    public ComplaintDTO(int id, String description, String date) {
        this.employee_id=id;
        this.description=description;
        this.date=date;
    }
}
