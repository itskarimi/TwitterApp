package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.PublicMessage;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.View;

public interface ViewService extends BaseEntityService<View, Long> {

    View addView(User viewer, PublicMessage viewed);
}
