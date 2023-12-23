package br.com.projetodifm.services;

import br.com.projetodifm.configs.FileStorageConfig;
import br.com.projetodifm.exceptions.FileStorageException;
import br.com.projetodifm.model.ImageFile;
import br.com.projetodifm.model.Produto;
import br.com.projetodifm.repositories.ImageFileRepository;
import br.com.projetodifm.repositories.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class UploadImageServices {

    private static final Logger LOGGER = Logger.getLogger(UploadImageServices.class.getName());

    private final Path fileStorageLocation;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ImageFileRepository imageFileRepository;

    @Autowired
    public UploadImageServices(FileStorageConfig fileStorageConfig){

        this.fileStorageLocation = Paths.get(fileStorageConfig.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation); // criando pasta se não existir
        } catch (Exception e){
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored", e);
        }
    }

    public ResponseEntity<?> uploadImageToProduct(List<MultipartFile> files, Long productId){

        LOGGER.info("Fazendo upload das imagens no produto");

        var user = AuthenticatedUsersServices.getAuthenticatedUser();

        var produto = produtoRepository.findByUserIdAndId(user.getId(), productId)
                .orElseThrow();

        files.forEach(x -> imageFileRepository.save(storeFile(x, produto)));

        LOGGER.info("Upload Completo");

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    private ImageFile storeFile(MultipartFile file, Produto produto){
        String filename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if(filename.contains(".."))
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + filename);

            Path targetLocation = this.fileStorageLocation.resolve(filename);

            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING); // Substituir caso já exista

            return ImageFile.builder()
                    .filename(filename)
                    .imageUri(targetLocation.toAbsolutePath().toString())
                    .size(file.getSize())
                    .fileType(file.getContentType())
                    .produto(produto)
                    .build();
        } catch (Exception e){
            throw new FileStorageException("Could not store file " + filename + ". Please try again", e);
        }
    }

}
