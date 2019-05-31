package com.github.abstractkim.springpractice.logging.loggingincommingreqeusts;

public class EventDto {
    Long id;
    String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public static final class EventDtoBuilder {
        Long id;
        String title;

        private EventDtoBuilder() {
        }

        public static EventDtoBuilder anEventDto() {
            return new EventDtoBuilder();
        }

        public EventDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public EventDtoBuilder title(String title) {
            this.title = title;
            return this;
        }

        public EventDto build() {
            EventDto eventDto = new EventDto();
            eventDto.setId(id);
            eventDto.setTitle(title);
            return eventDto;
        }
    }
}
