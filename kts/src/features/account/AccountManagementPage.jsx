import { Container, Box, Typography, Button } from '@mui/material'
import React, {useState} from 'react'
import { DataGrid } from '@mui/x-data-grid';
import CreateAccountModal from './CreateAccountModal';
import useGetAccounts from './useGetAccounts';


export default function AccountManagementPage() {
    const [isCreateAccountModalOpen, setIsCreateAccountModalOpen] = useState(false);
    
    const handleOpen = () => setIsCreateAccountModalOpen(true);
    const handleClose = () => setIsCreateAccountModalOpen(false);

    const accounts = useGetAccounts();
    
    return (
    <div>
      <Container maxWidth="md">
        <Box sx={{ bgcolor: '#cfe8fc', height: '90vh' }}>
            <Box display = 'flex' sx = {{height : '10%'}}>
                <Box flex= {1}>
                    <Typography>계좌 현황</Typography>
                </Box>
                <Box flex={3}>
                    <Button onClick={handleOpen}>계좌 개설하기</Button>
                    <CreateAccountModal 
                        isCreateAccountModalOpen = {isCreateAccountModalOpen}
                        handleClose = {handleClose}
                    />
                    <Button>계좌 삭제하기</Button>
                </Box>
            </Box>
            <Box sx = {{height : '50%'}}>
                <DataGrid
                    columns={[{field: 'accountNumber', headerName : "계좌번호"}, {field : 'accountBalance', headerName : "잔고"}]}
                    rows={accounts ? accounts : null}
                    />
                    
            </Box>
        </Box>
      </Container>
    </div>
  )
}
