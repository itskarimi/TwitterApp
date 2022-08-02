package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.ViewProfile;
import edu.sharif.twitter.repository.ViewProfileRepository;
import edu.sharif.twitter.service.ViewProfileService;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class ViewProfileServiceImpl extends BaseEntityServiceImpl<ViewProfile, Long, ViewProfileRepository>
    implements ViewProfileService {
    public ViewProfileServiceImpl(ViewProfileRepository repository) {
        super(repository);
    }

    private final EntityTransaction transaction = repository.getEntityManger().getTransaction();

    public ViewProfile addViewProfile(User viewer, User viewed) {
        ViewProfile viewProfile = new ViewProfile();
        viewProfile.setViewer(viewer);
        viewProfile.setViewed(viewed);
        viewProfile.setCreateDateTime(LocalDateTime.now());
        viewProfile.getViewed().getProfileViews().add(viewProfile);
        viewProfile.getViewer().getProfileActivities().add(viewProfile);

        transaction.begin();
        repository.save(viewProfile);
        transaction.commit();

        return viewProfile;
    }
}
