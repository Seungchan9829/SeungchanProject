import { useQuery } from '@tanstack/react-query'
import React from 'react'
import { getAccounts } from './api/accountApi'

export default function useGetAccounts() {
    const query = useQuery({
        queryKey: ['accounts'],
        queryFn : () => getAccounts()
    })

    return query.data;
}
