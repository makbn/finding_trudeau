package io.github.makbn.core.service.crawler;

import io.github.makbn.api.Post;

import java.util.Date;
import java.util.Set;

interface Crawler<T extends Post> {

    Set<T> getPosts(Date from, Date to, Integer limitation);
}
