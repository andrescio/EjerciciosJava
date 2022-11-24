package com.example.block11uploaddownloadfiles.file.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @Id
    @GeneratedValue
    private Integer fileId;

    @Lob
    private byte[] file;

    private String name;

    private String category;

    private Date upload_date;

    public File(byte[] file, String name, String category, Date upload_date) {
        this.file = file;
        this.name = name;
        this.category = category;
        this.upload_date = upload_date;
    }
}
