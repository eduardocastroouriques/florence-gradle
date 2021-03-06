package com.software.florence.resource;

import com.software.florence.common.exception.NegocioException;
import com.software.florence.common.pattern.application.resource.AbstractResource;
import com.software.florence.common.pattern.application.service.Service;
import com.software.florence.common.util.file.FileUtil;
import com.software.florence.common.util.ftp.FTPSender;
import com.software.florence.entity.ExameDoacao;
import com.software.florence.entity.ProcessoDoacao;
import com.software.florence.service.ExameDoacaoService;
import com.software.florence.service.NotificationServiceImpl;
import com.software.florence.service.ProcessoDoacaoService;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.SocketException;
import java.nio.file.Paths;

@RestController
@RequestMapping(path = "/exame-doacao")
public class ExameDoacaoResource extends AbstractResource<ExameDoacao, Long> {

    @Autowired
    ExameDoacaoService exameComplementarService;

    @Autowired
    ProcessoDoacaoService processoDoacaoService;

    @Autowired
    Environment environment;

    @Autowired
    FTPSender ftpSender;

    @Autowired
    FileUtil fileUtil;

    @Autowired
    NotificationServiceImpl notificationService;

    public ExameDoacaoResource(Service<ExameDoacao, Long> service) {
        super(service);
    }

    @GetMapping("/processo-doacao/{id}")
    public ResponseEntity<ExameDoacao> findByProcessoDoacaoId(@PathVariable Long id) {
        ExameDoacao retorno = null;
        try {

            ProcessoDoacao processoDoacao = processoDoacaoService.findById(id);
            retorno = this.exameComplementarService.findByProcessoDoacao(processoDoacao);
        } catch (NegocioException e) {
            return this.criarRespostaErro(e);
        } // try-catch
        return this.criarResposta(HttpStatus.OK, retorno);
    }// findById()

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("upload") MultipartFile multipartFile) {

        if (!multipartFile.isEmpty()) {

            fileUtil.saveFileLocally(multipartFile, environment.getProperty("florence.image.exame.doacao"));
            ftpSender.send(environment.getProperty("florence.image.exame.doacao"), multipartFile);

        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/notification")
    public ResponseEntity<ExameDoacao> push() {

        try {
            this.notificationService.push(null);
        } catch (NegocioException e) {
            return this.criarRespostaErro(e);
        } // try-catch
        return this.criarResposta(HttpStatus.OK, "FOI");
    }// findById()
}
