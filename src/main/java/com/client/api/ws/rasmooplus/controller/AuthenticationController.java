package com.client.api.ws.rasmooplus.controller;

import com.client.api.ws.rasmooplus.dto.LoginDTO;
import com.client.api.ws.rasmooplus.dto.TokenDTO;
import com.client.api.ws.rasmooplus.dto.UserDetailsDTO;
import com.client.api.ws.rasmooplus.model.redis.UserRecoveryCode;
import com.client.api.ws.rasmooplus.service.AuthenticationService;
import com.client.api.ws.rasmooplus.service.UserDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserDetailService userDetailsService;

    //    @ApiOperation(value = "Realiza a autenticacao do usuario")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Usuario e senha validados com sucesso"),
//            @ApiResponse(code = 400, message = "Usuario ou senha invalidos"),
//            @ApiResponse(code = 404, message = "Usuario nao encontrado")
//    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TokenDTO> auth(@RequestBody @Valid LoginDTO dto) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.auth(dto));
    }

    //    @ApiOperation(value = "Envia codigo de recuperacao para o email do usuario")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Codigo enviado com sucesso"),
//            @ApiResponse(code = 400, message = "Dados invalidos"),
//            @ApiResponse(code = 404, message = "Algum dado nao foi encontrado")
//    })
    @PostMapping(value = "/recovery-code/send",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendRecoveryCode(@RequestBody @Valid UserRecoveryCode dto) {
        userDetailsService.sendRecoveryCode(dto.getEmail());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }

    //    @ApiOperation(value = "Valida codigo de recuperacao")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Codigo de recuperacao e valido"),
//            @ApiResponse(code = 400, message = "Dados invalidos"),
//            @ApiResponse(code = 404, message = "Algum dado nao foi encontrado")
//    })
    @GetMapping(value = "/recovery-code/")
    public ResponseEntity<?> recoveryCodeIsValid(@RequestParam("recoveryCode") String recoveryCode,
                                                 @RequestParam("email") String email) {
        return ResponseEntity.status(HttpStatus.OK).body( userDetailsService.recoveryCodeIsValid(recoveryCode, email));
    }

    //    @ApiOperation(value = "Atualiza senha a partir de um codigo de recuperacao valido")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Senha atualizada com sucesso"),
//            @ApiResponse(code = 400, message = "Dados invalidos"),
//            @ApiResponse(code = 404, message = "Algum dado nao foi encontrado")
//    })
    @PatchMapping(value = "/recovery-code/password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePasswordByRecoveryCode(@RequestBody @Valid UserDetailsDTO dto) {
        userDetailsService.updatePasswordByRecoveryCode(dto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
