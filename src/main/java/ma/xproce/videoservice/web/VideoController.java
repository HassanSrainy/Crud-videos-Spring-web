package ma.xproce.videoservice.web;

import ma.xproce.videoservice.dao.entities.Video;
import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.service.VideoService;
import ma.xproce.videoservice.service.CreatorService;  // Assurez-vous que ce service existe
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private CreatorService creatorService;

    // Afficher la liste des vidéos
    @GetMapping
    public String getAllVideos(Model model) {
        List<Video> videos = videoService.getAllVideos();
        model.addAttribute("videos", videos); // Ajouter les vidéos au modèle
        return "videos"; // Retourne le nom de la vue (videos.html)
    }

    // Afficher le formulaire d'ajout de vidéo
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("video", new Video());  // Ajouter un objet Video vide
        List<Creator> creators = creatorService.getAllCreators();  // Récupérer tous les créateurs
        model.addAttribute("creators", creators);  // Passer la liste des créateurs au modèle
        return "add-video";  // Vue pour afficher le formulaire
    }

    // Ajouter une vidéo
    @PostMapping("/add")
    public String addVideo(@ModelAttribute Video video) {
        videoService.saveVideo(video);  // Sauvegarder la vidéo dans la base de données
        return "redirect:/videos";  // Rediriger vers la liste des vidéos
    }

    // Afficher le formulaire de modification d'une vidéo
    // Afficher le formulaire de modification d'une vidéo
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Video video = videoService.getVideoById(id);  // Méthode qui récupère la vidéo par ID

        if (video == null) {
            model.addAttribute("error", "Vidéo introuvable avec l'ID " + id);
            return "redirect:/videos";  // Rediriger vers la liste des vidéos avec un message d'erreur
        }

        model.addAttribute("video", video);  // Ajouter la vidéo au modèle
        List<Creator> creators = creatorService.getAllCreators();  // Ajouter la liste des créateurs
        model.addAttribute("creators", creators);

        return "edit-video";  // Retourner la vue pour l'édition de la vidéo
    }



    // Modifier une vidéo après soumission du formulaire
    @PostMapping("/edit/{id}")
    public String updateVideo(@PathVariable Long id, @ModelAttribute Video video) {
        videoService.updateVideo(id, video);  // Mettre à jour la vidéo
        return "redirect:/videos";  // Rediriger vers la liste des vidéos après modification
    }

    // Supprimer une vidéo
    @PostMapping("/delete/{id}")
    public String deleteVideo(@PathVariable Long id) {
        videoService.deleteVideo(id);  // Supprimer la vidéo
        return "redirect:/videos";  // Rediriger vers la liste des vidéos après suppression
    }
}



