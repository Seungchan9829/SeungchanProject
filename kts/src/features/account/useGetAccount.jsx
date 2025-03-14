import {useQuery} from "@tanstack/react-query";
import { getAccountInfo } from "./api/accountApi";



export default function useGetAccount(accountId) {
  const query = useQuery({
    queryKey : ['accounts', accountId],
    queryFn : () => getAccountInfo(accountId)
  }
  )
  return query.data;
}
