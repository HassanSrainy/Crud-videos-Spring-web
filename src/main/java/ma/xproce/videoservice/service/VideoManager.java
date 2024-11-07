package ma.xproce.videoservice.service;
import ma.xproce.videoservice.dao.entities.Video;
import ma.xproce.videoservice.dao.repositories.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VideoManager implements VideoService {

    @Autowired
    private VideoRepository videoRepository;

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public Video saveVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public Video updateVideo(Long id, Video video) {
        // Vérifier si la vidéo existe
        if (!videoRepository.existsById(id)) {
            throw new IllegalArgumentException("Video with id " + id + " not found");
        }

        // Définir l'ID de la vidéo et la sauvegarder
        video.setId(id);
        return videoRepository.save(video);
    }

    @Override
    public void deleteVideo(Long id) {
        videoRepository.deleteById(id);
    }
    @Override
    public Video getVideoById(Long id) {
        return videoRepository.findById(id).orElse(null);  // Utilisation de findById qui renvoie un Optional
    }

}