package br.com.projetodifm.data.vo.v1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UploadFileResponseVO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String filename;
    private String fileType;
    private String imageUri;
    private long size;
}
