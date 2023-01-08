import {useMemo} from "react";

// кількість сторінок для пагінації
export const getPageCount = (totalCount, limit) => {
    return Math.ceil(totalCount / limit)
}

// список сторінок пагінації
export const usePagesArray = (totalPages) => {
    const pagesArray = useMemo(() => {
        let pagesArr = []
        for (let i = 0; i < totalPages; i++) {
            pagesArr.push(i + 1);
        }
        return pagesArr;
    }, [totalPages])

    return pagesArray;
}

