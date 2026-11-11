package suda.com.practica.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ManejadorGlobal {

    // 404 - recurso no encontrado
    @ExceptionHandler(IllegalArgumentException::class)
    fun noEncontrado(e: IllegalArgumentException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(mapOf("error" to (e.message ?: "Recurso no encontrado")))

    // 409 - conflicto con reglas de negocio
    @ExceptionHandler(IllegalStateException::class)
    fun conflicto(e: IllegalStateException): ResponseEntity<Map<String, String>> =
        ResponseEntity.status(HttpStatus.CONFLICT)
            .body(mapOf("error" to (e.message ?: "Conflicto")))
}
