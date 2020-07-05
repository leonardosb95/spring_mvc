package br.com.casadocodigo.loja.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;


@Component//para que possa ser reconhecida pelo Spring
public class FileSaver {

    @Autowired
    private HttpServletRequest request;

    public String write(String baseFolder, MultipartFile file ){

        try {
            //String path=baseFolder + "/"+ file.getOriginalFilename();//monta o caminho do arquivo
            String realPath=request.getServletContext().getRealPath("/"+baseFolder);
            String path=realPath+"/"+file.getOriginalFilename();
            file.transferTo(new File(path));//Transfere para o caminho do servidor
            return baseFolder+"/"+file.getOriginalFilename();

        }catch (IllegalStateException | IOException e){
            throw new RuntimeException(e);
        }

    }


}
