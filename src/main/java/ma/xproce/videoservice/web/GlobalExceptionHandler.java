package ma.xproce.videoservice.web;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Gestion des erreurs 404 (Page non trouvée)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NoHandlerFoundException ex, Model model) {
        model.addAttribute("error", "La page demandée n'existe pas.");
        return "error";  // Retourne la vue d'erreur
    }

    // Gestion des autres exceptions (500, erreurs générales)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGenericException(Exception ex, Model model) {
        model.addAttribute("error", "Une erreur interne est survenue. Veuillez réessayer plus tard.");
        return "error";  // Retourne la vue d'erreur
    }

    // Gestion des erreurs 400 (Mauvaise requête)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleBadRequestException(IllegalArgumentException ex, Model model) {
        model.addAttribute("error", "La requête est invalide.");
        return "error";  // Retourne la vue d'erreur
    }
}
