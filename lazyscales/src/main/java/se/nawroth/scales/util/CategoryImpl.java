/**
 * Copyright (c) 2002-2012 "Neo Technology,"
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
package se.nawroth.scales.util;

import org.neo4j.graphdb.Node;

/**
 * Helper for implementing category elements.
 * 
 * @author Anders Nawroth
 * 
 * @param <T> the element type of the category
 * @param <U> the element type of the items
 */
@SuppressWarnings( "rawtypes" )
public abstract class CategoryImpl<T extends NamedEntity, U extends NamedEntity>
        extends NamedEntityImpl<T, U> implements NamedEntity<T>
{
    /**
     * Create instance from underlying {@link Node} and navigation
     * 
     * @param underlyingNode the underlying node
     * @param navigation navigation helper to use
     */
    protected CategoryImpl( final Node underlyingNode,
            final CategoryWithPropertiesUtil<T, U> navigation )
    {
        super( underlyingNode, navigation );
    }

    /**
     * Create a new category.
     * 
     * @param underlyingNode the underlying node
     * @param navigation the navigation helper to use
     * @param parent the parent category
     * @param name the name of the category
     */
    @SuppressWarnings( "unchecked" )
    protected CategoryImpl( final Node underlyingNode,
            final CategoryWithPropertiesUtil<T, U> navigation, final T parent,
            final String name )
    {
        super( underlyingNode, navigation );
        getNavigation().addCategory( parent, (T) this, NAME, name );
    }

    @SuppressWarnings( "unchecked" )
    @Override
    public final String getName()
    {
        return (String) getNavigation().getSingleCategoryRelationshipProperty(
                (T) this, NAME );
    }

    @Override
    public final int compareTo( final T other )
    {
        return getName().compareTo( other.getName() );
    }
}
