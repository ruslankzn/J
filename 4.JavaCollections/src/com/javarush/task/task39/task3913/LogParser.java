package com.javarush.task.task39.task3913;

import com.javarush.task.task39.task3913.query.IPQuery;
import com.javarush.task.task39.task3913.query.UserQuery;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.LogRecord;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptyList;

public class LogParser implements IPQuery, UserQuery {
    private Path logDir;

    private List<ParsedLog> logList=new ArrayList<>();

    public LogParser(Path logDir){
        File[] files=logDir.toFile().listFiles(
                (dir, name) -> name.endsWith(".log"));
        Arrays.stream(logDir.toFile().listFiles((dir, name) -> name.endsWith(".log"))).flatMap(x->{
            try {
                return Files.readAllLines(x.toPath()).stream();
            } catch(IOException e) {
                e.printStackTrace();
                return null;
            }
        }).forEach(x->logList.add(new ParsedLog(x)));
    }

    @Override
    public int getNumberOfUniqueIPs(Date after, Date before) {
        return getUniqueIPs(after,before).size();
    }

    @Override
    public Set<String> getUniqueIPs(Date after, Date before) {
        return getUniqueIpsFor(null,null,null,after,before);
    }

    @Override
    public Set<String> getIPsForUser(String user, Date after, Date before) {
        return getUniqueIpsFor(user,null,null,after,before);
    }

    @Override
    public Set<String> getIPsForEvent(Event event, Date after, Date before) {
        return getUniqueIpsFor(null,event,null,after,before);
    }

    @Override
    public Set<String> getIPsForStatus(Status status, Date after, Date before) {
        return getUniqueIpsFor(null,null,status,after,before);
    }

private Set<String> getUniqueIpsFor(String user, Event event, Status status, Date after, Date before){
    Stream<ParsedLog> logStream = logList.stream();
    if(after!=null)
        logStream = logStream.filter(x->x.getDate().after(after) || x.getDate().equals(after));
    if(before!=null)
        logStream = logStream.filter(x->x.getDate().before(before) || x.getDate().equals(before));
    if(user!=null)
        logStream=logStream.filter(x->x.getUser().equals(user));
    if(event!=null)
        logStream=logStream.filter(x->x.getEvent().equals(event));
    if(status!=null)
        logStream=logStream.filter(x->x.getStatus().equals(status));
    return logStream.map(x->x.getIp()).collect(Collectors.toSet());
}


    @Override
    public Set<String> getAllUsers() {
      //  List<ParsedLog> logsForPeriodList = getLogsForPeriod(null, null);
        Set<String> resultSet = new HashSet<>();
      //  for (ParsedLog log : logsForPeriodList) {
        //    resultSet.add(log.getName());
      //  }
        return resultSet;
    }

    @Override
    public int getNumberOfUsers(Date after, Date before) {
        return 0;
    }

    @Override
    public int getNumberOfUserEvents(String user, Date after, Date before) {
        return 0;
    }

    @Override
    public Set<String> getUsersForIP(String ip, Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getLoggedUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getDownloadedPluginUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getWroteMessageUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getSolvedTaskUsers(Date after, Date before, int task) {
        return null;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before) {
        return null;
    }

    @Override
    public Set<String> getDoneTaskUsers(Date after, Date before, int task) {
        return null;
    }
}