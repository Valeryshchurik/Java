package adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class HorseRaceIntAdapter extends XmlAdapter<String, Integer> {
    @Override
    public Integer unmarshal(String v) throws Exception {
        return Integer.parseInt(v.split("-")[1]);
    }

    @Override
    public String marshal(Integer v) throws Exception {
        return "HorseRace-" + v.toString();
    }
}
