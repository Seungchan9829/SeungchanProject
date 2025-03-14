import React from 'react'
import TopMenu from './TopBar'
import { Outlet } from 'react-router-dom'

const MainLayout = () => {
  return (
    <div >
      <TopMenu/>
      <Outlet/>
    </div>
  )
}

export default MainLayout
