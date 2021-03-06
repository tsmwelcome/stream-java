/**

 Copyright (c) 2015, Alessandro Pieri
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions are met:

 1. Redistributions of source code must retain the above copyright notice, this
 list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright notice,
 this list of conditions and the following disclaimer in the documentation
 and/or other materials provided with the distribution.

 THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 The views and conclusions contained in the software and documentation are those
 of the authors and should not be interpreted as representing official policies,
 either expressed or implied, of the FreeBSD Project.

 */
package io.getstream.client.model.feeds;

import io.getstream.client.exception.InvalidFeedNameException;
import io.getstream.client.repo.StreamRepository;

import java.util.regex.Pattern;

/**
 * Factory class to build a new instance of a feed.
 */
public final class BaseFeedFactory implements FeedFactory {

    private final static Pattern FEED_SLUG_PATTERN = Pattern.compile(FEED_SLUG_ALLOWED_PATTERN);
    private final static Pattern FEED_ID_PATTERN = Pattern.compile(FEED_ID_ALLOWED_PATTERN);

    private final StreamRepository streamRepository;

    public BaseFeedFactory(final StreamRepository streamRepository) {
        this.streamRepository = streamRepository;
    }

    @Override
    public Feed createFeed(final String feedSlug, final String id) throws InvalidFeedNameException {
        if (FEED_SLUG_PATTERN.matcher(feedSlug).matches() && FEED_ID_PATTERN.matcher(id).matches()) {
            return new BaseFeed(streamRepository, feedSlug, id);
        }
        throw new InvalidFeedNameException("Either feedSlug or id are not valid. Feed slug only accept words, feed id accepts words and hyphens");
    }
}
