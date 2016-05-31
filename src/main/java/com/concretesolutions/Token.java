package com.concretesolutions;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import java.security.Key;
import io.jsonwebtoken.Claims;

import java.util.Date;


/**
 * Classe responsável por agrupar métodos relacionados com tokens
 * Não deve ser instanciada
 */
public final class Token {
    public final static long UM_MINUTO_EM_MILISEGUNDOS = 60000;//milisegundos
    // public final static long VALIDADE_TOKEN = UM_MINUTO_EM_MILISEGUNDOS * 30;
    public final static long VALIDADE_TOKEN = 20000;
    public final static String SECRET_KEY = "4W966UpRB5P7e2u5z8UE2YNf0AlT974705O8J68rV46ea433X~71o25R9N5d925KlOH1e4^94229iH6Nf741oH32M2P8U49N48NI";


    /**
     * construtor private para evitar criação de objetos dessa classe
     */
    private Token() {}


    /**
     * Gera um token
     * @param subject O subject passado para o builder do JWT
     * @return token com expiração de NOW() + VALIDADE_TOKEN (em milisegundos)
     */
    public static String generateToken(String subject) {
        return Jwts.builder().setSubject(subject).setExpiration(new Date((new Date()).getTime() + VALIDADE_TOKEN)).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();
    }


    /**
     * Verifica se o token é válido
     * @param token Um token em formato de String
     * @return true se o token está no prazo de validade e se não houve problema de assinatura
     */
    public static boolean isNotExpired(String token) {
        // tenta fazer um parse do token
        try {
            Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
            // aqui poderia ser feita uma conta para ver se o token já expirou,
            // mas a lib jjwt já lança uma exception ao tentar fazer um parse
            // do token
            return true;
        }
        catch (ExpiredJwtException e) {
            // caso o token esteja expirado no parse, retorna false
            return false;
        }
    }
}
