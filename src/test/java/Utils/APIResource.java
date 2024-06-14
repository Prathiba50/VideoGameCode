package Utils;

public enum APIResource {

        getVedioGamesAPI("/videogames"),
        addVediogameAPI("/videogames"),
        deleteVedioGameAPI("/videogames/{videoGameId}");

        private String resource;
         APIResource(String resource){
                this.resource = resource;
        }

        public String getResource(){
                return resource;
        }

}
