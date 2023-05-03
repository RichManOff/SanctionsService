package com.example.sanction_service.batch;

import com.example.sanction_service.model.SanctionEntity;
import com.example.sanction_service.repository.SanctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SanctionService {
    private final SanctionRepository sanctionRepository;

//    private List<SanctionEntity> items = new ArrayList<>();

//    public List<SanctionEntity> saxmethod(SanctionEntity sanctionEntity){
//
//        return items;
//    }
    @Autowired
    public SanctionService(SanctionRepository sanctionRepository) {
        this.sanctionRepository = sanctionRepository;
    }

    public List<SanctionEntity> getAllSanctionList(){
        return sanctionRepository.findAll();
    }

    public SanctionEntity getByName(String name){
        return sanctionRepository.findByNameContainingIgnoreCase(name)
                .orElseThrow(() -> new IllegalStateException("University not found for this id :: " + name));
    }

    public SanctionEntity save(SanctionEntity sanctionEntity){
        return sanctionRepository.save(sanctionEntity);
    }

    public List<SanctionEntity> saveList(List<SanctionEntity> sanctions) {
        List<SanctionEntity> items = new ArrayList<>();
        try {
            SAXParserFactory fact = SAXParserFactory.newInstance();
            SAXParser saxParser = fact.newSAXParser();

            DefaultHandler handle = new DefaultHandler() {
                boolean bname = false;
                boolean bsurname = false;
                String name;
                String surname;
                Long id = 1L;

                public void startElement(String uri, String localName, String qName, Attributes attributes)
                        throws SAXException {
                    if (qName.equals("FIRST_NAME"))
                        bname = true;
                    if (qName.equals("SECOND_NAME"))
                        bsurname = true;
                }

                public void endElement(String uri, String localName, String qName) {
                    if (qName.equals("INDIVIDUAL")) {
                        SanctionEntity entity = new SanctionEntity();
                        entity.setId(id++);
                        entity.setName(name);
                        entity.setSurname(surname);
                        items.add(sanctionRepository.save(entity)); // Call save method for each entity
                    }
                }

                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (bname) {
                        name = new String(ch, start, length);
                        bname = false;
                    }
                    if (bsurname) {
                        surname = new String(ch, start, length);
                        bsurname = false;
                    }
                }
            };

            saxParser.parse("src/main/resources/xml/oqjp6en-dprk.xml", handle);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return items;
    }

}
