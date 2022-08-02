package edu.sharif.twitter.service.impl;

import edu.sharif.twitter.base.service.impl.BaseEntityServiceImpl;
import edu.sharif.twitter.entity.Like;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.View;
import edu.sharif.twitter.repository.ViewRepository;
import edu.sharif.twitter.service.ViewService;

import javax.persistence.EntityTransaction;
import java.time.LocalDateTime;

public class ViewServiceImpl extends BaseEntityServiceImpl<View, Long, ViewRepository>
    implements ViewService {
    public ViewServiceImpl(ViewRepository repository) {
        super(repository);
    }

    private final EntityTransaction transaction = repository.getEntityManger().getTransaction();

    public View addView(User viewer, PublicMessage viewed) {
        View view = new View();
        view.setViewer(viewer);
        view.setViewed(viewed);
        view.setCreateDateTime(LocalDateTime.now());
        view.getViewed().getViews().add(view);
        view.getViewer().getViews().add(view);

        transaction.begin();
        repository.save(view);
        transaction.commit();

        return view;
    }
}
