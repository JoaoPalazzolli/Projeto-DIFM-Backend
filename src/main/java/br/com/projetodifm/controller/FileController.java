package br.com.projetodifm.controller;

import br.com.projetodifm.services.UploadImageServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "File Endpoint")
@RestController
@RequestMapping("/api/v1/file")
public class FileController {

    @Autowired
    private UploadImageServices uploadImageServices;

    @PostMapping(value = "/uploadImageToProduct/{productId}")
    public ResponseEntity<?> uploadImageToProduct(
            @PathVariable(value = "productId") Long productId,
            @RequestParam(value = "files") List<MultipartFile> files){

        return uploadImageServices.uploadImageToProduct(files, productId);
    }


}