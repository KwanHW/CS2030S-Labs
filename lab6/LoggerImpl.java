import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.function.Function;

public class LoggerImpl<T> implements Logger<T> {
    private final T value;
    private final List<String> logs;

    private LoggerImpl(T value,List<String> logs) {
        this.value = value;
        this.logs = logs;
    }

    public LoggerImpl(T value) {
        this(value,List.of("Value initialized. Value = " + value));
    }
    
    public <T> String updateLogs(T value) {
        String newLog = "Value changed! New value = ";

        if (this.value.equals(value)) {
            newLog = "Value unchanged. Value = ";
        }

        return newLog+value;
    }

    @Override
    public T getValue() {
        return this.value;
    }

    @Override
    public List<String> getLogs() {
        return this.logs;
    }

    @Override
    public boolean test(Predicate<? super T> pred) {
        return pred.test(this.value);
    }

    @Override
    public <U> Logger<U> map(Function<? super T,? extends U> f) {
        U newValue = f.apply(this.value);

        List<String> newLogs = new ArrayList<String>(this.logs);
        newLogs.add(this.updateLogs(newValue));
        return new LoggerImpl<U>(newValue,newLogs);
    }

    @Override
    public <U> Logger<U> flatMap(Function<? super T,? extends Logger<? extends U>> f) {
        Logger<? extends U> uLog = f.apply(this.value);
        U newValue = uLog.getValue();

        // Merging Logs
        List<String> newLogs = new ArrayList<String>(this.logs);
        Iterator<String> uLogIt = uLog.getLogs().iterator();
        while(uLogIt.hasNext()) {
            String log = uLogIt.next();
            // Filters away initialised messages
            if (log.contains("Value initialized")) {
                continue;
            } else {
                newLogs.add(log);
            }
        }

        return new LoggerImpl<U>(newValue,newLogs);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj instanceof Logger) {
            @SuppressWarnings("unchecked")
            LoggerImpl<?> logObj = (LoggerImpl<?>)obj;
            return this.logs.equals(logObj.getLogs()) && this.value.equals(logObj.getValue());
        } else {
            return false;
        }
    }
    
    @Override
    public void printlog() {
        Iterator<String> strIt = this.logs.iterator();
        String log = "";

        // TODO Is there a better way to do this?
        while (strIt.hasNext()) {
            log += strIt.next();
            if (strIt.hasNext()) {
                log += "\n";
            }
        }
        System.out.println(log);
    }

    @Override
    public String toString() {
        return String.format("Logger[%s]",this.value);
    }
}



