package ca.bcit.ass2.rai_park;

/**
 * Created by Taran on 2017-10-07.
 */

public class Country {

        private String _name;
        private String _capital;
        private String _region;
        private String _population;
        private String _area;
        private String _borders;
        private String _flag;

        public void setName(String name) {
            _name = name;
        }

        public String getName() {
            return _name;
        }

        public void setCapital(String capital) {
            _capital = capital;
        }

        public String getCapital() {
            return _capital;
        }

        public void setRegion(String region) {
            _region = region;
        }

        public String getRegion() {
            return _region;
        }

        public void setPopulation(String population) {
            _population = population;
        }

        public String getPopulation() {
            return _population;
        }

        public void setArea(String area) {
            _area = area;
        }

        public String getArea() {
            return _area;
        }

        public void setBorders(String borders) {
            _borders = borders;
        }

        public String getBorders() {
            return _borders;
        }

        public void setFlag(String flag) {
            _flag = flag;
        }

        public String getFlag() {
            return _flag;
        }

        public String toString() {
            return _name;
        }
}
