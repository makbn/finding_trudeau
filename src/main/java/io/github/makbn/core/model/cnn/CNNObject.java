package io.github.makbn.core.model.cnn;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * by Mehdi Akbarian Rastaghi , 20/4/10
 **/

@Builder
@Data
public class CNNObject {

    private Set<Result> result;
    private Meta meta;

    @Builder
    public static class Meta {

        @JsonProperty("start")
        private int start;

        @JsonProperty("end")
        private int end;

        @JsonProperty("total")
        private int total;

        @JsonProperty("of")
        private int of;
    }

    @Builder
    public static class Result {
        private String _id;
        private String type;
        private String url;
        private String body;
        private String lastPublishDate;
        private String source;
        private String headline;
        private String thumbnail;

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getLastPublishDate() {
            return lastPublishDate;
        }

        public void setLastPublishDate(String lastPublishDate) {
            this.lastPublishDate = lastPublishDate;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getBody() {
            return body;
        }


        public void setBody(String body) {
            this.body = body;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getHeadline() {
            return headline;
        }

        public void setHeadline(String headline) {
            this.headline = headline;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }
    }
}
