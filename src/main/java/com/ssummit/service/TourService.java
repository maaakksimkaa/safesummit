package com.ssummit.service;

import com.ssummit.dto.*;
import com.ssummit.model.*;
import com.ssummit.repository.CheckpointMarkRepository;
import com.ssummit.repository.RouteRepository;
import com.ssummit.repository.TourRepository;
import com.ssummit.repository.UserRepository;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class TourService extends GenericService<Tour> {
    private final TourRepository repository;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;
    private final CheckpointMarkService checkpointMarkService;
    private final CheckpointMarkRepository checkpointMarkRepository;
    private final CheckpointService checkpointService;

    protected TourService(TourRepository repository, UserRepository userRepository, RouteRepository routeRepository, CheckpointMarkService checkpointMarkService, CheckpointMarkRepository checkpointMarkRepository, CheckpointService checkpointService) {
        super(repository);
        this.repository = repository;
        this.userRepository = userRepository;
        this.routeRepository = routeRepository;
        this.checkpointMarkService = checkpointMarkService;
        this.checkpointMarkRepository = checkpointMarkRepository;
        this.checkpointService = checkpointService;
    }

    public Tour scheduleTour(ScheduleTourDto scheduleTourDto) {
        Tour tour = new Tour();
        tour.setTitle(scheduleTourDto.getTitle());
        tour.setDescription(scheduleTourDto.getDescription());
        tour.setRoute(routeRepository.findById(scheduleTourDto.getRouteId()).orElseThrow());
        tour.setStartDate(scheduleTourDto.getStartDate());
        tour.setEndDate(scheduleTourDto.getStartDate().plusDays(tour.getRoute().getDuration()));
        tour.setCreatedBy("ADMIN");
        tour.setCreatedDateTime(LocalDateTime.now());
        tour.setUpdatedDateTime(LocalDateTime.now());
        tour.setIsDeleted(false);
        return create(tour);
    }

    public String getTourDescription(Long id) {
        return getOne(id).getDescription();
    }

    public TourEquipment getRequiredItems(Long tourId) {
        return getOne(tourId).getTourEquipment();
    }

    public Tour setPrimaryGuide(AddTourDto addTourDto) {
        User user = userRepository.findById(addTourDto.getUserId()).orElseThrow();
        Tour tour = getOne(addTourDto.getTourId());
        tour.setPrimaryGuide(user);
        return update(tour);
    }

    public Tour setSecondaryGuide(AddTourDto addTourDto) {
        User user = userRepository.findById(addTourDto.getUserId()).orElseThrow();
        Tour tour = getOne(addTourDto.getTourId());
        tour.setSecondaryGuide(user);
        return update(tour);
    }

    public Tour setRoute(AddRouteDto addRouteDto) {
        Tour tour = getOne(addRouteDto.getTourId());
        Route route = routeRepository.findById(addRouteDto.getRouteId()).orElseThrow();
        tour.setRoute(route);
        return update(tour);
    }

    public Tour addCheckpointMark(AddCheckpointMarkDto addCheckpointMarkDto) {
        Tour tour = getOne(addCheckpointMarkDto.getTourId());
        Checkpoint checkpoint = checkpointService.getOne(addCheckpointMarkDto.getCheckpointId());
        CheckpointMark checkpointMark = new CheckpointMark();
        checkpointMark.setCheckpoint(checkpoint);
        checkpointMark.setScheduledMarkedTime(addCheckpointMarkDto.getScheduledMarkedTime());
        checkpointMarkService.create(checkpointMark);
        tour.getCheckpointMarks().add(checkpointMark);
        return update(tour);
    }

    public Set<String> getRouteCheckpoints(Long tourId) {
        return getOne(tourId).getRoute().getRouteCheckpoints().stream()
                .map(Checkpoint::getDescription).collect(Collectors.toSet());
    }

    public Map<String, LocalDateTime> getScheduledCheckpointMarks(Long tourId) {
        Map<String, LocalDateTime> scheduledCheckpointMarks = new HashMap<>();
        Set<CheckpointMark> checkpointMarks = getOne(tourId).getCheckpointMarks();
        for (CheckpointMark mark :
                checkpointMarks) {
            scheduledCheckpointMarks.put(mark.getCheckpoint().getDescription(), mark.getScheduledMarkedTime());
        }
        return scheduledCheckpointMarks;
    }

    public List<Double> getLastCheckpointCoordinates(Long tourId) {
        CheckpointMark lastCheckpointMark = getLastPassedCheckMark(tourId);
        Checkpoint lastCheckpoint = lastCheckpointMark.getCheckpoint();
        List<Double> lastCheckpointCoordinates = new ArrayList<>();
        lastCheckpointCoordinates.add(lastCheckpoint.getLatitude());
        lastCheckpointCoordinates.add(lastCheckpoint.getLongitude());
        return lastCheckpointCoordinates;
    }

    public Map<LocalDateTime, String> getScheduledTours() {
        List<Tour> tours = repository.findAll();
        Map<LocalDateTime, String> tourSchedule = new HashMap<>();
        for (Tour tour :
                tours) {
            tourSchedule.put(tour.getStartDate(), tour.getDescription());
        }
        return tourSchedule;
    }

    public List<Tour> getAllActiveTour() {
        return repository.findByStartDateBeforeAndEndDateAfter(LocalDateTime.now(), LocalDateTime.now());
    }

    public CheckpointMark getNowCheckpointMark(Long tourId) {
        return checkpointMarkRepository.getFirstByTour_IdAndScheduledMarkedTimeBeforeAndActualMarkedTimeNullOrderByScheduledMarkedTimeAsc(tourId, LocalDateTime.now());
    }

    public CheckpointMark getLastPassedCheckMark(Long tourId) {
        return checkpointMarkRepository.getFirstByTour_IdAndActualMarkedTimeNotNullOrderByActualMarkedTimeAsc(tourId);
    }

    public Set<String> getRequiredItemTypes(Long tourId) {
        Set<ItemType> requiredItemTypes = getOne(tourId).getRoute().getRequiredItemTypes();
        return requiredItemTypes.stream().map(ItemType::getTitle).collect(Collectors.toSet());
    }

    public TourGuidesAndParticipantsDto getTourGuidesAndParticipants(Long tourId) {
        TourGuidesAndParticipantsDto dto = new TourGuidesAndParticipantsDto();
        dto.setPrimaryGuide(
                getOne(tourId).getPrimaryGuide().getFirstName() + " " + getOne(tourId).getPrimaryGuide().getLastName()
        );
        dto.setSecondaryGuide(
                getOne(tourId).getSecondaryGuide().getFirstName() + " " + getOne(tourId).getSecondaryGuide().getLastName()
        );
        Set<User> tourParticipants = getOne(tourId).getParticipants();
        Set<String> participantsNames = new HashSet<>();
        for (User user :
                tourParticipants) {
            participantsNames.add(user.getFirstName() + " " + user.getLastName());
        }
        dto.setParticipants(participantsNames);
        return dto;
    }

    public String checkTourBeforeDeparture(Long tourId) {
        Tour tour = getOne(tourId);

        if (Objects.isNull(tour.getPrimaryGuide()) && Objects.isNull(tour.getSecondaryGuide())) {
            return "На данный поход не назначено ни одного гида!";
        }
        if ((Objects.isNull(tour.getPrimaryGuide()) || Objects.isNull(tour.getSecondaryGuide()))
                && tour.getParticipants().size() > 5) {
            return "Одного гида недостаточно для такого количества участников!";
        }
        if (Objects.isNull(tour.getCheckpointMarks())) {
            return "План похода отсутствует!";
        }
        if (Objects.isNull(tour.getRoute().getRequiredItemTypes())) {
            return "Проверьте наличие списка требуемого оборудования на маршруте тура!";
        }
        if (Objects.isNull(tour.getTourApplication())) {
            return "Заявка на тур не отправлена в МЧС!";
        }
        try {
            if (!isWeatherSafe(tour.getRoute().getOWCityId())) {
                return "Неблагоприятный прогноз погоды!";
            }
        } catch (IOException e) {
            return "Проблемы с подключением к серверу погоды";
        }
        return "Группа готова к выходу на маршрут!";

    }

    private boolean isWeatherSafe(String cityId) throws IOException {
        JSONObject obj = getWeatherData(cityId);
        Double wind = obj.getJSONObject("wind").getDouble("speed");
        return wind.compareTo(15.0) < 0;
    }

    private JSONObject getWeatherData(String cityId) throws IOException {
        URL requestUrl = new URL("https://api.openweathermap.org/data/2.5/weather?id=" + cityId + "&appid=98699fa04f026a7730f87b9b026cecdf");

        HttpURLConnection connection = null;
        connection = (HttpURLConnection) requestUrl.openConnection();
        connection.connect();
        InputStream in;
        int status = 0;
        status = connection.getResponseCode();
        if (status != HttpURLConnection.HTTP_OK) {
            in = connection.getErrorStream();
        } else {
            in = connection.getInputStream();
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder sb = new StringBuilder();
        String line;
        while (true) {
            if ((line = reader.readLine()) == null) break;
            sb.append(line).append("\n");
        }
        in.close();

        return new JSONObject(sb.toString());
    }

}
