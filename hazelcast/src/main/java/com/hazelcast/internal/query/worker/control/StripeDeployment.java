package com.hazelcast.internal.query.worker.control;

import com.hazelcast.sql.impl.QueryContext;
import com.hazelcast.internal.query.exec.Exec;
import com.hazelcast.sql.impl.mailbox.AbstractInbox;
import com.hazelcast.sql.impl.mailbox.Outbox;

import java.util.List;

/**
 * Deployment of a single fragment.
 */
public class StripeDeployment {

    private final Exec exec;
    private final int sripe;
    private final int thread;
    private final List<AbstractInbox> inboxes;
    private final List<Outbox> outboxes;

    private QueryContext ctx;
    private FragmentDeployment fragmentDeployment;

    private volatile boolean done;

    public StripeDeployment(Exec exec, int stripe, int thread, List<AbstractInbox> inboxes, List<Outbox> outboxes) {
        this.exec = exec;
        this.sripe = stripe;
        this.thread = thread;
        this.inboxes = inboxes;
        this.outboxes = outboxes;
    }

    public Exec getExec() {
        return exec;
    }

    public int getSripe() {
        return sripe;
    }

    public int getThread() {
        return thread;
    }

    public List<AbstractInbox> getInboxes() {
        return inboxes;
    }

    public List<Outbox> getOutboxes() {
        return outboxes;
    }

    public boolean isDone() {
        return done;
    }

    // TODO: Exception, result, etc.
    public void onDone() {
        done = true;
    }

    public QueryContext getContext() {
        return ctx;
    }

    public FragmentDeployment getFragmentDeployment() {
        return fragmentDeployment;
    }

    public void initialize(QueryContext ctx, FragmentDeployment fragmentDeployment) {
        this.ctx = ctx;
        this.fragmentDeployment = fragmentDeployment;
    }
}