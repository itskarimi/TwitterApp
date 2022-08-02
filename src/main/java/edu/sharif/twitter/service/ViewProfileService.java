package edu.sharif.twitter.service;

import edu.sharif.twitter.base.service.BaseEntityService;
import edu.sharif.twitter.entity.User;
import edu.sharif.twitter.entity.ViewProfile;

public interface ViewProfileService extends BaseEntityService<ViewProfile, Long> {
    ViewProfile addViewProfile(User viewer, User viewed);
}
