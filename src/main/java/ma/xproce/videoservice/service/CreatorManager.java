package ma.xproce.videoservice.service;
import ma.xproce.videoservice.dao.entities.Creator;
import ma.xproce.videoservice.dao.repositories.CreatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CreatorManager implements CreatorService {

    @Autowired
    private CreatorRepository creatorRepository;

    @Override
    public List<Creator> getAllCreators() {
        return creatorRepository.findAll();
    }

    @Override
    public Creator saveCreator(Creator creator) {
        return creatorRepository.save(creator);
    }

    @Override
    public Creator updateCreator(Long id, Creator creator) {
        creator.setId(id);
        return creatorRepository.save(creator);
    }

    @Override
    public void deleteCreator(Long id) {
        creatorRepository.deleteById(id);
    }
}
