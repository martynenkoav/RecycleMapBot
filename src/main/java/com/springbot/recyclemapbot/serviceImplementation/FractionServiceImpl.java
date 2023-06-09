package com.springbot.recyclemapbot.serviceImplementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.springbot.recyclemapbot.DTO.FractionDTO;
import com.springbot.recyclemapbot.model.Fraction;
import com.springbot.recyclemapbot.repository.FractionRepository;
import com.springbot.recyclemapbot.service.FractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class FractionServiceImpl implements FractionService {

    private final FractionRepository fractionRepository;

    @Override
    public void save() throws IOException {
        URL url = new URL("https://new.recyclemap.ru/api/public/fractions");
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode arrayNode = (ArrayNode) mapper.readTree(url).get("data");
        for(JsonNode jsonNode : arrayNode) {
            FractionDTO fractionDTO = new FractionDTO(jsonNode.get("id").asInt(), jsonNode.get("name").asText(), jsonNode.get("color").asText());
            Fraction fraction = fractionDTO.FractionDTOtoFraction();
            this.fractionRepository.save(fraction);
        }
    }

    @Override
    public Fraction getFractionById(Integer id) {
        return this.fractionRepository.getFractionById(id);
    }

    public Set<String> getFractionIdsBySubscribeId(Long id){
        return this.fractionRepository.getFractionIdsBySubscribeId(id);
    }

    public Set<String> getFractionIdsByPointId(Long pointId) {
        return this.fractionRepository.getFractionIdsByPointId(pointId);
    }

    public Set<String> getFractionIdsByApplicationId(Long applicationId) {
        return this.fractionRepository.getFractionIdsByApplicationId(applicationId);
    }
}
