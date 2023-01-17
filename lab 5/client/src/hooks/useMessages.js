import {useMemo} from "react";

export const useSortedMessages = (messages, sort) => {
    const sortedMessages = useMemo(() => {
        if (sort) {
            return [...messages].sort((a, b) => a[sort].localeCompare(b[sort], 'uk'))
        }
        return messages;
    }, [sort, messages])

    return sortedMessages;
}

export const useMessages = (messages, sort, query) => {
    const sortedMessages = useSortedMessages(messages, sort);
    const sortedAndSearchedMessages = useMemo(() => {
        return sortedMessages.filter((message => message.id))
    }, [query, sortedMessages])

    return sortedAndSearchedMessages;
}