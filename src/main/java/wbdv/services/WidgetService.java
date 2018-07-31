package wbdv.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wbdv.models.Topic;
import wbdv.models.Widget;
import wbdv.repositories.TopicRepository;
import wbdv.repositories.WidgetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController

@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @Autowired
    TopicRepository topicRepository;

    @GetMapping("/api/widget")
    public List<Widget> findAllWidgets() {
        return (List<Widget>) widgetRepository.findAll();
    }

    @GetMapping("/api/widget/{widgetId}")
    public Widget findWidgetById(@PathVariable("widgetId") int widgetId) {
        Optional<Widget> data = widgetRepository.findById(widgetId);
        return data.orElse(null);
    }

    @GetMapping("/api/topic/{topicId}/widget")
    public List<Widget> findAllWidgetsForTopic(
            @PathVariable("topicId") int topicId) {
        Optional<Topic> data = topicRepository.findById(topicId);
        if (data.isPresent()) {
            Topic course = data.get();
            return widgetRepository.findAllWidgetsByTopicSorted(course);
        }
        return null;
    }

    @PostMapping("/api/topic/{topicId}/widget")
    public Widget createWidget(
            @PathVariable("topicId") int topicId,
            @RequestBody Widget newWidget) {
        Optional<Topic> data = topicRepository.findById(topicId);
        if (data.isPresent()) {
            Topic topic = data.get();
            newWidget.setTopic(topic);
            return widgetRepository.save(newWidget);

        }
        return null;
    }

    @PostMapping("/api/topic/{topicId}/widgets")
    public List<Widget> createWidgets(
            @PathVariable("topicId") int topicId,
            @RequestBody List<Widget> newWidgets) {
        widgetRepository.deleteWidgetsByLessonId(topicId);
        List<Widget> output = new ArrayList<Widget>();
        for (Widget w : newWidgets) {
            output.add(createWidget(topicId, w));
        }
        return output;
    }

    @DeleteMapping("/api/widget/{widgetId}")
    public void deleteWidget(@PathVariable("widgetId") int widgetId) {
        widgetRepository.deleteById(widgetId);
    }



    @PutMapping("/api/widget/{widgetId}")
    public Widget updateWidget(
            @PathVariable("widgetId") int widgetId,
            @RequestBody Widget newWidget) {
        Optional<Widget> data = widgetRepository.findById(widgetId);
        if (data.isPresent()) {
            Widget widget = data.get();
            widget.setName(newWidget.getName());
            widget.setWidgetOrder(newWidget.getWidgetOrder());
            widget.setText(newWidget.getText());
            widget.setSize(newWidget.getSize());
            widget.setListType(newWidget.getListType());
            widget.setWidgetType(newWidget.getWidgetType());
            widget.setLinkName(newWidget.getLinkName());
            widgetRepository.save(widget);
            return widget;
        }
        return null;
    }


    @PostMapping("/api/widget/save")
    public void saveAllWidgets(@RequestBody List<Widget> widgets) {
        widgetRepository.deleteAll();
        for (Widget widget : widgets) {
            widgetRepository.save(widget);
        }
    }


    @PostMapping("/api/widget/save/{topicId}")
    public List<Widget> saveAllWidgets(@RequestBody List<Widget> widgets, @PathVariable("topicId") int topicId) {
        Optional<Topic> topicData = topicRepository.findById(topicId);
        List<Widget> response = new ArrayList<>();
        if (topicData.isPresent()) {
            List<Widget> widList = topicData.get().getWidgets();
            widgetRepository.deleteAll(widList);
            for (Widget w : widgets) {
                w.setTopic(topicData.get());
                response.add(widgetRepository.save(w));
            }
            return response;
        } else {
            return new ArrayList<>();
        }
    }
}