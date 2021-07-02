# Ingegneria del Software - PROVA FINALE - 2020-2021

# Group ID: GC46

## Masters of Renaissance
This project was commissioned by Politecnico di Milano. It consists in creating an implementation of [Masters of Renaissance](https://craniointernational.com/products/masters-of-renaissance/) board game using Java, following the MVC design pattern.

## Students
- [Piferi Francesco](https://github.com/francescopiferi99)
- [Rosa Michelangelo](https://github.com/michelangelorosa)
- [Rossi Riccardo](https://github.com/redrick99)

## Professor
- Gianpaolo Cugola

## Implemented Functionalities
| Functionality | Status |
|:-----------------------|:------------------------------------:|
| ***Basic rules*** | ![GREEN](http://placehold.it/15/44bb44/44bb44) |
| ***CLI*** |![GREEN](http://placehold.it/15/44bb44/44bb44) |
| ***Socket*** |![GREEN](http://placehold.it/15/44bb44/44bb44) |
| Complete rules | ![GREEN](http://placehold.it/15/44bb44/44bb44) |
| GUI | ![GREEN](http://placehold.it/15/44bb44/44bb44) |
| Resilience | ![GREEN](http://placehold.it/15/44bb44/44bb44) |
| Persistence | ![GREEN](http://placehold.it/15/44bb44/44bb44) |
| Param Modifier | ![RED](http://placehold.it/15/f03c15/f03c15) |
| Multiple games | ![RED](http://placehold.it/15/f03c15/f03c15)|
| Local Match | ![RED](http://placehold.it/15/f03c15/f03c15)|

[![GREEN](http://placehold.it/15/44bb44/44bb44)]() Implemented &nbsp;&nbsp;&nbsp;&nbsp; [![RED](http://placehold.it/15/f03c15/f03c15)]() Not Implemented

## Documentation

### [Initial UML](https://github.com/michelangelorosa/ing-sw-2021-Piferi-Rosa-Rossi/tree/master/deliverables/Initial-UML)

### [Final UML](https://github.com/michelangelorosa/ing-sw-2021-Piferi-Rosa-Rossi/blob/master/deliverables/Final-UML/ing-sw-2021-Piferi-Rosa-Rossi_complete.pdf)

### [Generated JavaDOC](https://github.com/michelangelorosa/ing-sw-2021-Piferi-Rosa-Rossi/tree/master/deliverables/Report-Coverage-Javadoc/Javadoc)

## Test Cases
| Package | Class Coverage | Method Coverage | Line Coverage |
|---------------|-----------|-----------|-----------| 
| Model | 97% | 82% | 66% |
| View | 44% | 53% | 41% |
| Controller | 100% | 98% | 92% |

For more information, see [Generated Test Coverage](https://github.com/michelangelorosa/ing-sw-2021-Piferi-Rosa-Rossi/tree/master/deliverables/Report-Coverage-Javadoc/Coverage)
- Model line tests are only at 66% because a big chunk (JSON related stuff) of the Model cannot be tested as it creates files and it reads from created files.
## Requirements
Java JDK version 15 (or higher) is required to correctly run the JAR files.

## Run Procedure
### Run the server (it is recommended to run the JAR file inside a subfolder as other files will be created)
#### USING DEFAULT PORT (8765)
```
java -jar server.jar
```
#### USING A GIVEN PORT
```
java -jar server.jar --port [portnumber]
```
where \[portnumber\] is the number of the port to use. If the chosen number is out of range or corresponds to a standard port on the router, the server will start on the default port (8765).

### Run the client using:
#### CLI
```
java -jar client.jar --cli
```
#### GUI
```
java -jar client.jar
``` 
- Client jar name depends on the specific OS Version used.
- For Windows only: to enable the colours in cmd.exe it is necessary to run the .bat file found inside the Windows folder.

#### Keep in Mind
To correctly run the Game in GUI mode, one must use the client.jar file corresponding to his own Operative System.

## Added Functionalities info
### Persistence
If the server shuts down or is closed while a game is in progress, to start a new game *with new players*:
- restart the server and wait for 5 minutes without the players reconnecting ***OR***
- forcefully delete _persistence_info.txt_ file from the folder where server.jar is stored and restart the server

## Further Information
- It is recommended to keep server.jar file in a folder as new files will be created during execution
- It is possible to activate some cheats in the CLI by typing "cheat" at the beginning of your turn.

