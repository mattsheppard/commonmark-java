package org.commonmark.renderer.commonmark;

import org.commonmark.renderer.NodeRenderer;

/**
 * Factory for instantiating new node renderers when rendering is done.
 */
public interface CommonMarkNodeRendererFactory {

    /**
     * Create a new node renderer for the specified rendering context.
     *
     * @param context the context for rendering (normally passed on to the node renderer)
     * @return a node renderer
     */
    NodeRenderer create(CommonMarkNodeRendererContext context);
}
