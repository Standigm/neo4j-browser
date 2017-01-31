import React from 'react'
import { connect } from 'react-redux'
import { withBus } from 'react-suber'
import * as editor from 'shared/modules/editor/editorDuck'
import { LabelItems, RelationshipItems, PropertyItems } from './MetaItems'
import UserDetails from './UserDetails'
import DatabaseKernelInfo from './DatabaseKernelInfo'
import ListItem from 'grommet/components/ListItem'
import {Drawer, DrawerBody, DrawerHeader} from 'nbnmui/drawer'

import styles from './style_meta.css'

export const DatabaseInfo = ({ labels = [], relationshipTypes = [], properties = [], userDetails, databaseKernelInfo, onItemClick }) => {
  return (
    <Drawer id='db-drawer'>
      <DrawerHeader title='Database Information' />
      <DrawerBody>
        <ListItem className={styles.section} disabled>
          <LabelItems labels={labels.map((l) => l.val)} onItemClick={onItemClick} />
        </ListItem>
        <ListItem className={styles.section} disabled>
          <RelationshipItems relationshipTypes={relationshipTypes.map((l) => l.val)} onItemClick={onItemClick} />
        </ListItem>
        <ListItem className={styles.section} disabled>
          <PropertyItems properties={properties.map((l) => l.val)} onItemClick={onItemClick} />
        </ListItem>
        <ListItem className={styles.section} disabled>
          <UserDetails userDetails={userDetails} onItemClick={onItemClick} />
          <DatabaseKernelInfo databaseKernelInfo={databaseKernelInfo} onItemClick={onItemClick} />
        </ListItem>
      </DrawerBody>
    </Drawer>
  )
}

const mapStateToProps = (state) => {
  return state.meta || {}
}
const mapDispatchToProps = (_, ownProps) => {
  return {
    onItemClick: (cmd) => {
      ownProps.bus.send(editor.SET_CONTENT, editor.setContent(cmd))
    }
  }
}

export default withBus(connect(mapStateToProps, mapDispatchToProps)(DatabaseInfo))
