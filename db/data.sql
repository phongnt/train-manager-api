-- Auto-generated SQL script #202102030115
INSERT INTO public.trains
  (id,"name",description,"distance-between-stop","max-speed","sharing-tracks","grade-crossing","train-frequency",amenities)
VALUES
  (1,'Light rail','Light rail, which might be also known as trolley and streetcars, mean trains that function as local transit in an urban-core and can operate on the street-level. Compared to rapid transit, light rail costs less, is more pedestrian friendly, but has less passenger capacity. The major advantage with light rail is that it can operate like rapid transit or like local buses, depending on the available infrastructure','a few blocks to 1 or 2 miles','55-65 mph',true,false,'3-30 minutes','n/a'),
  (2,'Rapid transit','Rapid transit, which is also known as metro, subway, and heavy rail, mean trains that generally serve the urban-core, have large passenger capacity, and operate totally separate from road traffic. In order to run separately from road traffic in the city-core, rapid transit trains would run either above or underground.','1/2 mile to 2 or 3 miles','65-70 mph',false,false,'3-20 minutes','Large space for standees'),
  (3,'Commuter rail','Commuter trains generally mean trains connecting suburban areas with the central city and primarily serves riders to and from work. Commuter trains typically run on weekdays, during rush hours, and only in the peak directions.','1 to 2 miles','79 mph',true,true,'15 minutes to hourly (some operate only during weekday peak hours)','Restroom');