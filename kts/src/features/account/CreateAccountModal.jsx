import { Modal, Box, Typography, TextField, Button } from '@mui/material'
import React, {useState, useEffect} from 'react'
import { createAccount } from './api/accountApi';

export default function CreateAccountModal(props) {
    const [accountNumber, setAccountNumber] = useState('');
    const [accountPassword, setAccountPassword] = useState('');

    useEffect(() => {
        if(!props.isCreateAccountModalOpen){
            setAccountNumber('')
            setAccountPassword('')
        }
    }, [props.isCreateAccountModalOpen])
    
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await createAccount({accountNumber : accountNumber, accountPassword : accountPassword})
            alert("계좌가 개설되었습니다.")
        } catch(error) {
            alert("계좌 개설에 실패하였습니다.")
        }
      };
    return (
    <div>
      <Modal
        open = {props.isCreateAccountModalOpen}
        onClose = {props.handleClose}
        >
            
        <Box 
          width='50%'
          height= '50%' 
          sx={{
            position: 'absolute',
            top: '50%',
            left: '50%',
            transform: 'translate(-50%, -50%)',
            bgcolor: 'background.paper',
            p: 4,
          }}
        >
          <Typography id="modal-modal-title" variant="h6" component="h2">
            계좌 개설
          </Typography>
          <TextField
          label="계좌 번호"
          variant="outlined"
          fullWidth
          margin="normal"
          value={accountNumber}
          onChange={(e) => setAccountNumber(e.target.value)}
        />
        <TextField
          label="계좌 비밀번호"
          variant="outlined"
          type="password"
          fullWidth
          margin="normal"
          value={accountPassword}
          onChange={(e) => setAccountPassword(e.target.value)}
        />
        <Button onClick = {(e) => handleSubmit(e)}variant="contained" color="primary" type="submit" sx={{ mt: 2 }}>
          계좌 개설하기
        </Button>
        </Box>
      </Modal>
    </div>
  )
}
