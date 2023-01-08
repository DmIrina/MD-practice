import {useMemo} from "react";

export const useSortedSessions = (sessions, sort) => {
    const sortedSessions = useMemo(() => {
        if (sort) {
            return [...sessions].sort((a, b) => a[sort].localeCompare(b[sort], 'uk'))
        }
        return sessions;
    }, [sort, sessions])

    return sortedSessions;
}

export const useSessions = (sessions, sort, query) => {
    const  sortedSessions = useSortedSessions(sessions, sort);
    const sortedAndSearchedSessions = useMemo(() => {
        return sortedSessions.filter((session => session.name.toLowerCase().includes(query.toLowerCase())))
    }, [query, sortedSessions])

    return sortedAndSearchedSessions;
}