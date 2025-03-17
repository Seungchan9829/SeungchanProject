import { useMutation, useQueryClient } from '@tanstack/react-query'
import React from 'react'
import { transcationProcess } from './api/accountApi';

export default function useTransactionProcess({accountId, type, amount}) {
  const queryClient = useQueryClient();

  const mutation = useMutation({
    mutationFn: () => {
        return transcationProcess({accountId, type, amount})
    },
    onSuccess : () => {
        queryClient.invalidateQueries([['accounts', accountId]])

    },
    onError : () => {
        console.error("에러발생")
    }
  })

  return mutation
}
