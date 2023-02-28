package com.ssummit.mapper;

import com.ssummit.dto.RouteDto;
import com.ssummit.model.Checkpoint;
import com.ssummit.model.ItemType;
import com.ssummit.model.Route;
import com.ssummit.repository.CheckpointRepository;
import com.ssummit.repository.ItemTypeRepository;
import com.ssummit.repository.RouteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RouteMapper extends GenericMapper<Route, RouteDto> {

    private final ModelMapper mapper;

    private final RouteRepository routeRepository;
    private final CheckpointRepository checkpointRepository;
    private final ItemTypeRepository itemTypeRepository;

    protected RouteMapper(ModelMapper mapper, RouteRepository routeRepository, CheckpointRepository checkpointRepository, ItemTypeRepository itemTypeRepository) {
        super(mapper, Route.class, RouteDto.class);
        this.mapper = mapper;
        this.routeRepository = routeRepository;
        this.checkpointRepository = checkpointRepository;
        this.itemTypeRepository = itemTypeRepository;
    }

    @PostConstruct
    public void setMapper() {
        mapper.createTypeMap(Route.class, RouteDto.class)
                .addMappings(m -> m.skip(RouteDto::setCheckpointsIds))
                .addMappings(m -> m.skip(RouteDto::setRequiredItemTypesIds))
                .setPostConverter(toDtoConverter());
        mapper.createTypeMap(RouteDto.class, Route.class)
                .addMappings(m -> m.skip(Route::setRouteCheckpoints))
                .addMappings(m -> m.skip(Route::setRequiredItemTypes))
                .setPostConverter(toEntityConverter());
    }

    @Override
    void mapSpecificFields(RouteDto source, Route destination) {
        destination.setRouteCheckpoints(checkpointRepository.findAllByIdIn(source.getCheckpointsIds()));
        destination.setRequiredItemTypes(itemTypeRepository.findAllByIdIn(source.getRequiredItemTypesIds()));
    }

    @Override
    void mapSpecificFields(Route source, RouteDto destination) {
        destination.setCheckpointsIds(getCheckpointsIds(source));
        destination.setRequiredItemTypesIds(getRequiredItemTypesIds(source));
    }

    private Set<Long> getCheckpointsIds(Route route) {
        return Objects.isNull(route) || Objects.isNull(route.getId())
                ? null
                : route.getRouteCheckpoints().stream()
                .map(Checkpoint::getId)
                .collect(Collectors.toSet());
    }

    private Set<Long> getRequiredItemTypesIds(Route route) {
        return Objects.isNull(route) || Objects.isNull(route.getId())
                ? null
                : route.getRequiredItemTypes().stream()
                .map(ItemType::getId)
                .collect(Collectors.toSet());
    }
}
