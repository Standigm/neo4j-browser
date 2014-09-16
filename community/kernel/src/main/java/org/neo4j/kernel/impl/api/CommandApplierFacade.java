/**
 * Copyright (c) 2002-2014 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.neo4j.kernel.impl.api;

import java.io.IOException;

import org.neo4j.helpers.collection.Visitor;
import org.neo4j.kernel.impl.index.IndexCommand.AddNodeCommand;
import org.neo4j.kernel.impl.index.IndexCommand.AddRelationshipCommand;
import org.neo4j.kernel.impl.index.IndexCommand.CreateCommand;
import org.neo4j.kernel.impl.index.IndexCommand.DeleteCommand;
import org.neo4j.kernel.impl.index.IndexCommand.RemoveCommand;
import org.neo4j.kernel.impl.index.IndexDefineCommand;
import org.neo4j.kernel.impl.nioneo.xa.command.Command;
import org.neo4j.kernel.impl.nioneo.xa.command.Command.LabelTokenCommand;
import org.neo4j.kernel.impl.nioneo.xa.command.Command.NeoStoreCommand;
import org.neo4j.kernel.impl.nioneo.xa.command.Command.NodeCommand;
import org.neo4j.kernel.impl.nioneo.xa.command.Command.PropertyCommand;
import org.neo4j.kernel.impl.nioneo.xa.command.Command.PropertyKeyTokenCommand;
import org.neo4j.kernel.impl.nioneo.xa.command.Command.RelationshipCommand;
import org.neo4j.kernel.impl.nioneo.xa.command.Command.RelationshipGroupCommand;
import org.neo4j.kernel.impl.nioneo.xa.command.Command.RelationshipTypeTokenCommand;
import org.neo4j.kernel.impl.nioneo.xa.command.Command.SchemaRuleCommand;
import org.neo4j.kernel.impl.nioneo.xa.command.NeoCommandHandler;

public class CommandApplierFacade implements NeoCommandHandler, Visitor<Command, IOException>
{
    private final NeoCommandHandler[] handlers;

    public CommandApplierFacade( NeoCommandHandler... handlers )
    {
        this.handlers = handlers;
    }
    
    @Override
    public void apply()
    {
        throw new UnsupportedOperationException( "This method should not be called, only I call apply() methods" );
    }

    @Override
    public void close()
    {
        try
        {
            for ( int i = handlers.length; i-- > 0; )
            {
                handlers[i].apply();
            }
        }
        finally
        {
            for ( int i = handlers.length; i-- > 0; )
            {
                handlers[i].close();
            }
        }
    }

    @Override
    public boolean visit( Command element ) throws IOException
    {
        element.handle( this );
        return true;
    }

    @Override
    public boolean visitNodeCommand( NodeCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitNodeCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitRelationshipCommand( RelationshipCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitRelationshipCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitPropertyCommand( PropertyCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitPropertyCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitRelationshipGroupCommand( RelationshipGroupCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitRelationshipGroupCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitRelationshipTypeTokenCommand( RelationshipTypeTokenCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitRelationshipTypeTokenCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitPropertyKeyTokenCommand( PropertyKeyTokenCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitPropertyKeyTokenCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitSchemaRuleCommand( SchemaRuleCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitSchemaRuleCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitNeoStoreCommand( NeoStoreCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitNeoStoreCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitLabelTokenCommand( LabelTokenCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitLabelTokenCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitIndexRemoveCommand( RemoveCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitIndexRemoveCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitIndexAddNodeCommand( AddNodeCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitIndexAddNodeCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitIndexAddRelationshipCommand( AddRelationshipCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitIndexAddRelationshipCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitIndexDeleteCommand( DeleteCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitIndexDeleteCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitIndexCreateCommand( CreateCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitIndexCreateCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }

    @Override
    public boolean visitIndexDefineCommand( IndexDefineCommand command ) throws IOException
    {
        boolean result = true;
        for ( NeoCommandHandler handler : handlers )
        {
            if ( !handler.visitIndexDefineCommand( command ) )
            {
                result = false;
            }
        }
        return result;
    }
}
